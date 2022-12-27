const fs = require('fs');



// Read the file into a string

const data = fs.readFileSync('path/to/file.json', 'utf8');



// Add square brackets at the beginning and end of the string

const modifiedData = '[' + data + ']';



// Parse the modified string into a JavaScript object

const jsonObject = JSON.parse(modifiedData);



console.log(jsonObject);  // Output: [{ ...contents of file... }]

