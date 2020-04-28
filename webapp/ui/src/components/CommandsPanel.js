import React, {useState} from 'react';
import {setGame} from "../redux/cards/actions";
import {connect} from "react-redux";

const gameStatus = {
  "notStarted": "Press Deal to start game",
  "started": "Game started. Press Hit or Stand button."
}

const CommandsPanel = (props) => {
  const [disabledDeal, setDisabledDeal] = useState(false)
  const [disabledHit, setDisabledHit] = useState(true)
  const [disabledStand, setDisabledStand] = useState(true)
  const [statusMessage, setStatusMessage] = useState(gameStatus.notStarted)
  const [gameId, setGameId] = useState(null)

  const dealButtonHandler =() => {
    props.setGame()
   // createGame()
    //  .then(handleGame)
    setDisabledDeal(true)
    setDisabledHit(false)
    setDisabledStand(false)
  }

  return (
    <div id="panel">
      <div id="status">{statusMessage}</div>
      <button id="deal" className="blue" type="button" disabled={disabledDeal} onClick={dealButtonHandler}>DEAL</button>
      <button id="hit" className="green" type="button" disabled={disabledHit}>HIT</button>
      <button id="stand" className="red" type="button" disabled={disabledStand}>STAND</button>
    </div>
  )
}
const mapStateToProps = (state) => {
  return {
    appData: state.App
  }
}

export default connect(mapStateToProps, {setGame}) (CommandsPanel);
