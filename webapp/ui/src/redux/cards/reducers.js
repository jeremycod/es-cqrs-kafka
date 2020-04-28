import {
  SET_GAME, SET_GAME_SUCCESS, SET_GAME_FAILURE
} from "../actionTypes";
import { REHYDRATE } from 'redux-persist';

const INIT_STATE = {
  gameId: "",
  dealerCards: [],
  playerCards: []
};

const App = (state= INIT_STATE, action) => {
  switch (action.type) {
    case SET_GAME:
      return { ...state, loading: true}
    case SET_GAME_SUCCESS:
      const data = action.payload.data
      return {
        ...state,
        gameId: data.gameId,
        dealerCards: data.newState.dealerCards,
        playerCards: data.newState.playerCards,
        loading: false
      }
    case SET_GAME_FAILURE:
      return {
        ...state
      }
    case REHYDRATE:
      const rehydrated = (action && action.payload && action.payload.App) || {}
      return { ...state, ...rehydrated }
    default: return { ...state };
  }
}

export default App
