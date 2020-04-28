import React  from 'react';
import Card from "./Card";
import {connect} from "react-redux";
import {setGame} from "../redux/cards/actions";
const PlayerBoard = (props) => {
  return (
    <div id="player-board">
      <div id="player-score" className="score">Your score: <span>0</span>
      </div>
      <div className="container" id="player">
        {props.appData.playerCards.map(function(card, index){
          return <Card rank={card.rank} key={index} suit={card.suit}/>
        })}
      </div>
    </div>
  )
}

const mapStateToProps = (state) => {
  return {
    appData: state.App
  }
}

export default connect(mapStateToProps, {setGame}) (PlayerBoard);
