import {all, fork, put, takeLatest} from 'redux-saga/effects';
import {SET_GAME} from "../actionTypes";
import {setGameFailure, setGameSuccess} from "./actions";
const baseUrl = '/api/v1'
 function* setGame(){
   try {
     const json = yield fetch(baseUrl + '/game', {
       method: 'POST',
       accept: "application/json"
     }).then(response => response.json())
     yield put(setGameSuccess(json))

   }catch(error){
     yield put(setGameFailure(error))

   }
 }

 export function* watchStartGame() {
   yield takeLatest(SET_GAME, setGame)
}

function * appSaga() {
  yield all([
    fork(watchStartGame)
  ])
}
export default appSaga;
