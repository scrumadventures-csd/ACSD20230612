const bowling = require('../src/bowling');

test('checks knocked down pins', () => {
    expect(bowling.knockedPins(2)).toBe(2);
});

test('checks is bowling', () => {
    expect(bowling.isBowling()).toBe(true);
});