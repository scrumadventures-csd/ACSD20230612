const bowling = require('../src/bowling');

test('checks knocked down pins', () => {
    expect(bowling.knockedPins(true,2)).toBe(2);
});