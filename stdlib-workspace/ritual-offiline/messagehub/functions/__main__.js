// Import packages
const lib = require('lib')({token: process.env.STDLIB_TOKEN});
const firebase = require("firebase-admin");
const serviceAccount = require("../serviceAccountKey.json");

require('es6-promise').polyfill();
require('isomorphic-fetch');

// Initialize imports
firebase.initializeApp({
  credential: firebase.credential.cert(serviceAccount),
  databaseURL: "https://hack-the-6ix-201-1535217409242.firebaseio.com/"
})

/**
 * Generic MessageBird SMS handler
 * @param {string} sender The phone number that sent the text to be handled
 * @param {string} receiver The StdLib phone number that received the SMS
 * @param {string} message The contents of the SMS
 * @param {string} createdDatetime Datetime when the SMS was sent
 * @returns {any}
 */

module.exports = async (sender = '', receiver = '', message = '_', createdDatetime = '', context) => {
  // Break down the message to the command_message
  let parsedMessage = message.split(" #").map((word) => word.trim().replace("#", ""));
  console.log(parsedMessage);


  // Find which handler to route to



  let handler = message.toLowerCase().trim().replace(/[^a-z0-9_-]/gi, '_') || '_';
  // console.log(handler);
  // let command_message = false;
  //
  // if (handler.charAt(0) == '#') {
  //   command_message = true;
  // }



  // let db = firebase.database();
  // let ref = db.ref("switch");
  // let value = await ref.once("value", snapshot => {
  //   console.log(snapshot.val());
  // });
  //
  //
  //
  // let response = await fetch('http://api.open-notify.org/iss-now.json')
  //   .then(resp => {
  //     if (resp.status >= 400) {
  //       throw new Error('Bad response from server');
  //     }
  //     return resp.json();
  //   });
  // console.log(response);


  try {
    result = await lib[`${context.service.identifier}.messaging.${handler}`]({
      sender: sender,
      message: message,
      receiver: receiver,
      createdDatetime: createdDatetime
    })
  } catch (e) {
    // Catch thrown errors specifically so we can log them. See logs using
    // $ lib logs <username>.<service name> from the command line
    console.error(e)
    return
  }
  return result
}
