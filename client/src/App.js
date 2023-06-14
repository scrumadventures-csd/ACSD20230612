import logo from './logo.svg';
import './App.css';
import RapidTable from "./components/RapidTables/RapidTable";


function App() {
  return (
    <main>
        <RapidTable
            data={[
                {
                    player: "",
                    "1": "",
                    "2": "",
                    "3": "",
                    "4": "",
                    "5": "",
                    "6": "",
                    "7": "",
                    "8": "",
                    "9": "",
                    "10": "",
                    total: ""
                }
            ]}
            columns={[
                {
                    name: "Player",
                    data: "player"
                },
                {
                    name: "1",
                    data: "1"
                },
                {
                    name: "2",
                    data: "2"
                },
                {
                    name: "3",
                    data: "3"
                },
                {
                    name: "4",
                    data: "4"
                },
                {
                    name: "5",
                    data: "5"
                },
                {
                    name: "6",
                    data: "6"
                },
                {
                    name: "7",
                    data: "7"
                },
                {
                    name: "8",
                    data: "8"
                },
                {
                    name: "9",
                    data: "9"
                },
                {
                    name: "10",
                    data: "10"
                },
                {
                    name: "Total",
                    data: "total"
                }
            ]}
            search={false}
            pagination={false}
        />
    </main>
  );
}

export default App;
