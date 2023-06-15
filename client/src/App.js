import './App.css';
import React, {useState, useEffect} from 'react';
import RapidTable from "./components/RapidTables/RapidTable";


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
                {data.frameScore}
            </span>
        </>
    )
};

function App() {
    const [playing, setPlaying] = useState(false);

    const handleRoll = () => {

    }

    return (
        <main>
            {!playing ?
                <div className={"h-full p-0 m-0 flex items-center justify-center flex-col"}>
                    <button className={"text-white bg-indigo-700 border border-indigo-600 rounded py-1 px-5 mt-4 hover:bg-indigo-500"} onClick={() => {setPlaying(true)}}>Play!</button>
                </div>
            :
                <>
                    <button className={"text-5xl absolute top-4 right-4 bg-neutral-500 rounded-full h-8 w-8 text-white bg-indigo-700 border border-indigo-600 rounded py-1 px-5 mt-4 hover:bg-indigo-500"} onClick={() => {setPlaying(false)}}>X</button>
                    <div className={"h-full p-0 m-0 flex items-center justify-center flex-col"}>
                        <div className={"bg-white rounded min-w-[80%]"}>
                            <RapidTable
                                data={[
                                    {
                                        player: "Player 1",
                                        "1": {
                                            firstBall: 8,
                                            secondBall: 1,
                                            frameScore: 9
                                        },
                                        "2": {
                                            firstBall: -1,
                                            secondBall: -1,
                                            frameScore: -1
                                        },
                                        "3": {
                                            firstBall: -1,
                                            secondBall: -1,
                                            frameScore: -1
                                        },
                                        "4": {
                                            firstBall: -1,
                                            secondBall: -1,
                                            frameScore: -1
                                        },
                                        "5": {
                                            firstBall: -1,
                                            secondBall: -1,
                                            frameScore: -1
                                        },
                                        "6": {
                                            firstBall: -1,
                                            secondBall: -1,
                                            frameScore: -1
                                        },
                                        "7": {
                                            firstBall: -1,
                                            secondBall: -1,
                                            frameScore: -1
                                        },
                                        "8": {
                                            firstBall: -1,
                                            secondBall: -1,
                                            frameScore: -1
                                        },
                                        "9": {
                                            firstBall: -1,
                                            secondBall: -1,
                                            frameScore: -1
                                        },
                                        "10": {
                                            firstBall: -1,
                                            secondBall: -1,
                                            frameScore: -1
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
                        <div className={"min-w-[80%]"}>
                            <button className={"text-white bg-indigo-700 border border-indigo-600 rounded py-1 px-5 mt-4 hover:bg-indigo-500"} onClick={() => {}}>Roll</button>
                        </div>
                    </div>
                </>
            }

        </main>
    );
}

export default App;
