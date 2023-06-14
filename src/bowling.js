const sessionId = undefined;

const utils = {
    fetchBowlingAPI: () => {
        const axios = require('axios');

        axios.request({
            method: 'get',
            maxBodyLength: Infinity,
            url: 'http://pinsetter.herokuapp.com/pinsetter/?action=register&frames=10&pins=10&rolls=2&test=1-2-3-4-5-6-7-8-9/X12',
            headers: { }
        })
        .then((response) => {
            console.log(JSON.stringify(response.data));
        })
        .catch((error) => {
            console.log(error);
        });
    }
}

const bowling = {
    knockedPins: function(
        bowling,
        knockedPins
    ) {
        return knockedPins;
    }
}
module.exports = bowling;