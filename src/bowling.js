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


    async registerGame({frames,pins,rolls,test}) {

        await axios.request({
            method: 'get',
            maxBodyLength: Infinity,
            url: 'http://pinsetter.herokuapp.com/pinsetter/?action=register&frames=10&pins=10&rolls=2&test=1-2-3-4-5-6-7-8-9/X12',
            headers: { }
        })
            .then((response) => {
                return response.data.id;
            })
            .catch((error) => {
                console.log(error);
            });
        return undefined;
    }
}
module.exports = bowling;
