const lib = require('lib')({
  token: process.env.STDLIB_TOKEN
})
const send = require('../../helpers/send.js');
const firebase = require("firebase-admin");
const serviceAccount = require("../../serviceAccountKey.json");

require('es6-promise').polyfill();
require('isomorphic-fetch');

let db = null;

/**
 * MORE handler, responds if user texts "more"
 *  (or any uppercase variation like "MORE")
 * @param {string} sender The phone number that sent the text to be handled
 * @param {string} receiver The StdLib phone number that received the SMS
 * @param {string} message The contents of the SMS
 * @param {string} createdDatetime Datetime when the SMS was sent
 * @returns {any}
 */
module.exports = async (sender = '', receiver = '', message = '', createdDatetime = '', context) => {
  console.log('restaurantsHandler');
  if (!db) {
    await firebase.initializeApp({
      credential: firebase.credential.cert(serviceAccount),
      databaseURL: "https://hack-the-6ix-201-1535217409242.firebaseio.com/"
    });
    db = await firebase.database()



    let parsedMessage = message.split(" #").map((word) => word.trim().replace("#", ""));

    //Test firebase
    let ref = db.ref("restaurants/");
    let snapshot = await ref.once("value");

    let dataJSON = snapshot.val();
    let restaurants = Object.keys(dataJSON).map((word) => " " + word);
    console.log(restaurants);

    return send(
      receiver,
      sender,
      `The available restaurants is ${restaurants}`
    )

  }
}
