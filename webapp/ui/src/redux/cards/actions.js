import {
  SET_GAME, SET_GAME_SUCCESS, SET_GAME_FAILURE
} from "../actionTypes";


export const setGame = () => ({
  type: SET_GAME,
  payload: {}
})
export const setGameSuccess = (data) => ({
  type: SET_GAME_SUCCESS,
  payload: {data}
})
export const setGameFailure = (error) => ({
  type: SET_GAME_FAILURE,
  payload: {error: error}
})
