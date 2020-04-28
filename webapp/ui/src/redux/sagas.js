import appSaga from "./cards/saga";
import {all} from "@redux-saga/core/effects";

function* rootSaga() {
  yield all([
    appSaga()
  ]);
}
export default rootSaga;
