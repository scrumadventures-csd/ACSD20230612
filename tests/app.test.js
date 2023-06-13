const bowling = require('../src/bowling');

test('checks knocked down pins', () => {
    expect(bowling.kockedPins(true,2)).toBe({bowling:true,knockedPins:2});
});