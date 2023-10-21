import nltk
nltk.download('popular')
from nltk.stem import WordNetLemmatizer
lemmatizer = WordNetLemmatizer()
import pickle
import numpy as np
from flask_cors import CORS
from keras.models import load_model
import os
import json
import random
import string
import re
from static.emo_unicode import UNICODE_EMOJI

url_pattern = re.compile(r'http\S+')
emoji_pattern = re.compile('|'.join(UNICODE_EMOJI), flags=re.UNICODE)

# rootPath = 'E:\\DoAnPhatTrienHeThongThongMinh\\ChatbotApp'
type = 'true'# true or wrong
model = load_model(os.path.abspath('model/'+type+'/model.h5'))

# intents = json.loads(open('data.json').read())
intents = json.loads(open(os.path.abspath('data/'+type+'/trainning/trainning.json'), encoding="utf8").read())
words = pickle.load(open(os.path.abspath('model/'+type+'/texts.pkl'), 'rb'))
classes = pickle.load(open(os.path.abspath('model/'+type+'/labels.pkl'), 'rb'))


def clean_up_sentence(sentence):
    # ignore special characters
    sentence_trans = sentence.translate(str.maketrans('', '', string.punctuation))
    # ignore link
    sentence_trans = url_pattern.sub(r'', sentence_trans)
    # ignore emotions
    sentence_trans = emoji_pattern.sub(r'', sentence_trans)
    # tokenize the pattern - split words into array
    sentence_words = nltk.word_tokenize(sentence_trans)

    # stem each word - create short form for word
    sentence_words = [lemmatizer.lemmatize(word.lower()) for word in sentence_words]
    print(sentence_words)

    return sentence_words

# return bag of words array: 0 or 1 for each word in the bag that exists in the sentence

def bow(sentence, words, show_details=True):
    # tokenize the pattern
    sentence_words = clean_up_sentence(sentence)

    # bag of words - matrix of N words, vocabulary matrix
    bag = [0]*len(words)
    for s in sentence_words:
        for i,w in enumerate(words):
            if w == s:
                print(w)
                # assign 1 if current word is in the vocabulary position
                bag[i] = 1
                if show_details:
                    print ("found in bag: %s" % w)
    return(np.array(bag))

def predict_class(sentence, model):
    # filter out predictions below a threshold
    p = bow(sentence, words,show_details=False)
    res = model.predict(np.array([p]))[0]
    ERROR_THRESHOLD = 0.25
    results = [[i,r] for i,r in enumerate(res) if r>ERROR_THRESHOLD]
    # sort by strength of probability
    results.sort(key=lambda x: x[1], reverse=True)
    return_list = []
    for r in results:
        return_list.append({"intent": classes[r[0]], "probability": str(r[1])})
    return return_list

def getResponse(ints, intents_json):
    tag = ints[0]['intent']
    list_of_intents = intents_json['intents']
    for i in list_of_intents:
        if(i['tag']== tag):
            result = random.choice(i['responses'])
            break
    return result



def chatbot_response(msg):
    ints = predict_class(msg, model)
    res = getResponse(ints, intents)
    return res

from flask import Flask, render_template, request

app = Flask(__name__)
cors = CORS(app)
app.static_folder = 'static'

@app.route('/chatbot', methods=['POST'])
def chatbot():
    message = request.get_data(as_text=True)
    
    # Xử lý câu hỏi từ ứng dụng Java Spring Boot và trả về phản hồi của chatbot
    response = chatbot_response(message)
    
    return response

# Các đoạn mã khác ở đây...

if __name__ == '__main__':
    app.run()