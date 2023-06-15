const bowling = require('../src/bowling');

test('checks knocked down pins', () => {
    expect(bowling.knockedPins(2)).toBe(2);
});

test('bowling api registers a new game', async () => {
    const gameId = await bowling.registerGame({
        frames: 1,
        pins: 1,
        rolls: 1
    })
    expect(gameId).toMatch(/[0-9]{4}-[0-9]{13}/);
})

test('bowling api returns the expected score', async () => {
    const gameId = await bowling.registerGame({
        frames: 1,
        pins: 10,
        rolls: 1,
        test: '2'
    })
    const result = await bowling.roll(gameId)
    expect(result).toBe(2);
});