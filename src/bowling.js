const axios = require('axios');
const bowling = {
    
    startBowling: function (){
        return true;
    },

    isBowling: function (){
        return true;
    },

    knockedPins: function(
        knockedPins
    ) {
        return knockedPins;
    },

    async registerGame({
        frames,
        pins,
        rolls,
        test=''
    }) {
        return await axios.request({
            method: 'get',
            maxBodyLength: Infinity,
            url: `http://pinsetter.herokuapp.com/pinsetter/?action=register&frames=${frames}&pins=${pins}&rolls=${rolls}&test=${test}`,
            headers: { }
        }).then((response) => {
            return response.data;
        }).catch((error) => {
            console.log(error);
            return 'Game failed to register';
        });
    },

    async roll(
        gameId
    ) {
        return await axios.request({
            method: 'get',
            maxBodyLength: Infinity,
            url: `http://pinsetter.herokuapp.com/pinsetter/?action=roll&id=${gameId}`,
            headers: { }
        }).then((response) => {
            return response.data;
        }).catch((error) => {
            console.log(error);
            return 'Game failed to register';
        });
    }
}
module.exports = bowling;
