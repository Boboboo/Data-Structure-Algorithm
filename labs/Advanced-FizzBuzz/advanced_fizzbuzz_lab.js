function fizzBuzzer(array) {
  for (var i = 0; i < array.length; i++) {
    if (array[i] % 15 == 0)
      console.log("BuzzFizz");
    else if (array[i] % 3 == 0)
      console.log("Buzz");
    else if (array[i] % 5 == 0)
      console.log("Fizz");
    else
      console.log(array[i]);
  }
}

var sequence = [];
for (var i = 10; i <= 250; i++)
  sequence.push(i);
fizzBuzzer(sequence);
