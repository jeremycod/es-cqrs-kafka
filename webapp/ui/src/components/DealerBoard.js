import React  from 'react';
import {connect} from "react-redux";
import {setGame} from "../redux/cards/actions";
import Card from "./Card";
const DealerBoard = (props) => {
  return (
    <div id="dealer-board">
      <div id="dealer-score" className="score">Dealer
        score: <span>0</span>
      </div>
      <div id="deck">
        <div className="back"></div>
      </div>
      <div className="container" id="dealer">
        {props.appData.dealerCards.map(function(card, index){
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

export default connect(mapStateToProps, {setGame}) (DealerBoard);

