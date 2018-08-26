const lib = require('lib')({ token: process.env.STDLIB_TOKEN })
const send = require('../../helpers/send.js');
const firebase = require("firebase-admin");
const serviceAccount = require("../../serviceAccountKey.json");

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
  // Initialize firebase app
  if (!db) {
    await firebase.initializeApp({
      credential: firebase.credential.cert(serviceAccount),
      databaseURL: "https://hack-the-6ix-201-1535217409242.firebaseio.com/"
    });
    db = await firebase.database()
  }

  // Parse message into usable elements
  let parsedMessage = message.split(" #").map((word) => word.trim().replace("#", ""));
  if (parsedMessage.length == 1) {
    return send(
      receiver,
      sender,
      `Please specify a <restaurant_name> after the command <reserve_times>`
    )
  }

  let restuarantName = parsedMessage[1];

  // Query Firebase
  let ref = db.ref("restaurants/" + restuarantName);
  let snapshot = await ref.once("value");

  let dataJSON = snapshot.val();
  let reservations = dataJSON['reservations'];
  let tableForFour = '';
  let tableForEight = '';

  if (reservations['table_4'] == 'None' && reservations['table_8'] == 'None') {
    return send(
      receiver,
      sender,
      `There are no available reservations times for booking at ${restuarantName}`
    )
  }
  else {
    for (const key of Object.keys(reservations['table_4'])) {
      tableForFour += "\n" + key + ": " + reservations['table_4'][key];
    }
    for (const key of Object.keys(reservations['table_8'])) {
      tableForEight += "\n" + key + ": " + reservations['table_8'][key];
    }

    return send(
      receiver,
      sender,
      `Available reservation times for today are:\nTable for 4: ${tableForFour} \n\nTable for 8: ${tableForEight}`
    )
  }

}
