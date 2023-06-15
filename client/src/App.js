import './App.css';
import React, {useState, useEffect} from 'react';
import RapidTable from "./components/RapidTables/RapidTable";
import { library } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { fas } from '@fortawesome/free-solid-svg-icons';
import { faBowlingBall, faCircle, faXmark } from '@fortawesome/free-solid-svg-icons';
import { faMoon, faStar, faFaceGrinStars } from '@fortawesome/free-regular-svg-icons';

library.add(fas, faBowlingBall, faCircle, faMoon, faStar, faFaceGrinStars)


const renderCellScore = ({
    data
}) => {
    return (
        <>
            <div className={"flex flex-row justify-end -mr-3 -mt-3"}>
                <div className={"p-2 border border-r-0 border-t-0 border-black text-lg rounded-bl"}>{data.firstBall}</div>
                <div className={"p-2 border border-r-0 border-t-0 border-black text-lg"}>{data.secondBall}</div>
            </div>
            <span className={"block text-center text-xl py-4"}>
                {data.firstBall + data.secondBall}
            </span>
        </>
    )
};

function App() {
    const [playing, setPlaying] = useState(false);

    return (
        <main>
            <FontAwesomeIcon icon="fa-regular fa-face-grin-stars" className={"text-white text-[15rem] absolute opacity-[.05] -z-10"} style={{"top":"2%", "left":"84%"}}/>
            <FontAwesomeIcon icon="fa-regular fa-face-grin-stars" className={"text-white text-[15rem] absolute opacity-[.05] -z-10"} style={{"top":"57%", "left":"70%"}}/>
            <FontAwesomeIcon icon="fa-regular fa-face-grin-stars" className={"text-white text-[15rem] absolute opacity-[.05] -z-10"} style={{"top":"28%", "left":"30%"}}/>
            <FontAwesomeIcon icon="fa-regular fa-face-grin-stars" className={"text-white text-[15rem] absolute opacity-[.05] -z-10"} style={{"top":"6%", "left":"16%"}}/>

            <FontAwesomeIcon icon="fa-regular fa-moon" className={"text-white text-[15rem] absolute opacity-[.05] -z-10"} style={{"top":"89%", "left":"6%"}}/>
            <FontAwesomeIcon icon="fa-regular fa-moon" className={"text-white text-[15rem] absolute opacity-[.05] -z-10"} style={{"top":"30%", "left":"70%"}}/>
            <FontAwesomeIcon icon="fa-regular fa-moon" className={"text-white text-[15rem] absolute opacity-[.05] -z-10"} style={{"top":"39%", "left":"50%"}}/>
            <FontAwesomeIcon icon="fa-regular fa-moon" className={"text-white text-[15rem] absolute opacity-[.05] -z-10"} style={{"top":"66%", "left":"37%"}}/>
            <FontAwesomeIcon icon="fa-regular fa-moon" className={"text-white text-[15rem] absolute opacity-[.05] -z-10"} style={{"top":"90%", "left":"85%"}}/>

            <FontAwesomeIcon icon="fa-regular fa-star" className={"text-white text-[15rem] absolute opacity-[.05] -z-10"} style={{"top":"85%", "left":"18%"}}/>
            <FontAwesomeIcon icon="fa-regular fa-star" className={"text-white text-[15rem] absolute opacity-[.05] -z-10"} style={{"top":"10%", "left":"46%"}}/>
            <FontAwesomeIcon icon="fa-regular fa-star" className={"text-white text-[15rem] absolute opacity-[.05] -z-10"} style={{"top":"81%", "left":"52%"}}/>
            <FontAwesomeIcon icon="fa-regular fa-star" className={"text-white text-[15rem] absolute opacity-[.05] -z-10"} style={{"top":"52%", "left":"5%"}}/>
            <FontAwesomeIcon icon="fa-regular fa-star" className={"text-white text-[15rem] absolute opacity-[.05] -z-10"} style={{"top":"40%", "left":"91%"}}/>

            {!playing ?
                <div className={"h-full p-0 m-0 flex items-center justify-center flex-col"}>
                    <FontAwesomeIcon icon="fa-solid fa-bowling-ball" mask={"fa-solid fa-circle"} className={"text-[20rem] bg-lime-500 rounded-full bg-gradient-to-br from-lime-400 to-black"} />
                    <button className={"text-white bg-indigo-700 border border-indigo-600 rounded py-1 px-5 mt-4 hover:bg-indigo-500"} onClick={() => {setPlaying(true)}}>Play!</button>
                </div>
            :
                <>
                    <FontAwesomeIcon icon="fa-solid fa-xmark" className={"text-white text-5xl absolute top-4 right-4 bg-neutral-500 rounded-full h-8 w-8 cursor-pointer"} onClick={() => {setPlaying(false)}}/>
                    <div className={"h-full p-0 m-0 flex items-center justify-center flex-col"}>
                        <div className={"bg-white rounded min-w-[80%]"}>
                            <RapidTable
                                data={[
                                    {
                                        player: "UNOBerry",
                                        "1": {
                                            firstBall: 8,
                                            secondBall: 1
                                        },
                                        "2": {
                                            firstBall: -1,
                                            secondBall: 0
                                        },
                                        "3": {
                                            firstBall: 0,
                                            secondBall: 0
                                        },
                                        "4": {
                                            firstBall: 0,
                                            secondBall: 0
                                        },
                                        "5": {
                                            firstBall: 0,
                                            secondBall: 0
                                        },
                                        "6": {
                                            firstBall: 0,
                                            secondBall: 0
                                        },
                                        "7": {
                                            firstBall: 0,
                                            secondBall: 0
                                        },
                                        "8": {
                                            firstBall: 0,
                                            secondBall: 0
                                        },
                                        "9": {
                                            firstBall: 0,
                                            secondBall: 0
                                        },
                                        "10": {
                                            firstBall: 0,
                                            secondBall: 0
                                        },
                                        total: ""
                                    },
                                    {
                                        player: "Alonso",
                                        "1": {
                                            firstBall: 8,
                                            secondBall: 1
                                        },
                                        "2": {
                                            firstBall: -1,
                                            secondBall: 0
                                        },
                                        "3": {
                                            firstBall: 0,
                                            secondBall: 0
                                        },
                                        "4": {
                                            firstBall: 0,
                                            secondBall: 0
                                        },
                                        "5": {
                                            firstBall: 0,
                                            secondBall: 0
                                        },
                                        "6": {
                                            firstBall: 0,
                                            secondBall: 0
                                        },
                                        "7": {
                                            firstBall: 0,
                                            secondBall: 0
                                        },
                                        "8": {
                                            firstBall: 0,
                                            secondBall: 0
                                        },
                                        "9": {
                                            firstBall: 0,
                                            secondBall: 0
                                        },
                                        "10": {
                                            firstBall: 0,
                                            secondBall: 0
                                        },
                                        total: ""
                                    }
                                ]}
                                columns={[
                                    {
                                        name: "Player",
                                        data: "player",
                                        dataRender: ({data}) => {
                                            return (<><span className={"text-2xl"}>{data}</span></>)
                                        }
                                    },
                                    {
                                        name: "1",
                                        data: "1",
                                        dataRender: renderCellScore
                                    },
                                    {
                                        name: "2",
                                        data: "2",
                                        dataRender: renderCellScore
                                    },
                                    {
                                        name: "3",
                                        data: "3",
                                        dataRender: renderCellScore
                                    },
                                    {
                                        name: "4",
                                        data: "4",
                                        dataRender: renderCellScore
                                    },
                                    {
                                        name: "5",
                                        data: "5",
                                        dataRender: renderCellScore
                                    },
                                    {
                                        name: "6",
                                        data: "6",
                                        dataRender: renderCellScore
                                    },
                                    {
                                        name: "7",
                                        data: "7",
                                        dataRender: renderCellScore
                                    },
                                    {
                                        name: "8",
                                        data: "8",
                                        dataRender: renderCellScore
                                    },
                                    {
                                        name: "9",
                                        data: "9",
                                        dataRender: renderCellScore
                                    },
                                    {
                                        name: "10",
                                        data: "10",
                                        dataRender: renderCellScore
                                    },
                                    {
                                        name: "Total",
                                        data: "total"
                                    }
                                ]}
                                search={false}
                                pagination={false}
                            />
                        </div>
                    </div>
                </>
            }

        </main>
    );
}

export default App;
