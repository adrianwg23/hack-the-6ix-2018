const lib = require('lib')({
  token: process.env.STDLIB_TOKEN
})
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
      `Please specify a <restaurant_name> after the command <details>`
    )
  }

  let restuarantName = parsedMessage[1];

  // Query Firebase
  let ref = db.ref("restaurants/" + restuarantName);
  let snapshot = await ref.once("value");

  let dataJSON = snapshot.val();
  let location = dataJSON['location'];
  let waitTime = dataJSON['wait_time'];
  let hoursOfOperation = `Monday:${dataJSON['hours']['Monday']}\nTuesday:${dataJSON['hours']['Tuesday']}\nWednesday:${dataJSON['hours']['Wednesday']}\nThursday:${dataJSON['hours']['Thursday']}\nFriday:${dataJSON['hours']['Friday']}\nSaturday:${dataJSON['hours']['Saturday']}\nSunday:${dataJSON['hours']['Sunday']}`;


  return send(
    receiver,
    sender,
    `${restuarantName} is located in "${location}"
    \nThe wait time for seating is approximately "${waitTime}"
    \nThe hours of operation is:\n"${hoursOfOperation}"`
  )
}
