const bowling = require('../src/bowling');

test('checks knocked down pins', () => {
    expect(bowling.knockedPins(2)).toBe(2);
});

test('bowling api returns the expected score', () => {
    const gameId = bowling.registerGame({
        frames: 1,
        pins: 10,
        rolls: 1,
        test: '2'
    })
    const result = bowling.roll({
        id: gameId,
    })
    expect(result).toBe(2);
});