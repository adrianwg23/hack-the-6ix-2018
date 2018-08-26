// Import packages
const lib = require('lib')({token: process.env.STDLIB_TOKEN});


require('es6-promise').polyfill();
require('isomorphic-fetch');

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
  let parsedMessage = message.split(" ").map((word) => word.trim());
  let validMessage = true;
  let handler;

  for (let command of parsedMessage) {
    if (command.charAt(0) != "#") {
      validMessage = false;
      break;
    }
  }
  if (validMessage) {
    handler = parsedMessage[0].replace("#", "");
  } else {
    handler = null;
  }

  console.log(parsedMessage);
  console.log(handler);

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
