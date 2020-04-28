
import React  from 'react';
import DealerBoard from "./DealerBoard";
import PlayerBoard from "./PlayerBoard";
import CommandsPanel from "./CommandsPanel";

const Dashboard = (props) => {
  return (

      <div className="App">
        <div id="table">
          <DealerBoard></DealerBoard>
          <div className="border-line"></div>
          <PlayerBoard></PlayerBoard>
        </div>
        <CommandsPanel></CommandsPanel>


      </div>

  )
}

export default Dashboard;
