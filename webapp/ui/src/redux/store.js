import { createStore, applyMiddleware, compose } from 'redux';
import storage from 'redux-persist/lib/storage' // defaults to localStorage for web
import createSagaMiddleware from "redux-saga";
import reducers from './reducers';
import sagas from "./sagas";
import {persistStore, persistReducer} from 'redux-persist'

const sagaMiddleware = createSagaMiddleware();

const persistConfig = {
  key: 'root',
  storage,
  whitelist: ['App'],
};

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

export function configureStore() {
  const reducer = persistReducer(persistConfig, reducers);
  const enhancer = composeEnhancers(applyMiddleware(sagaMiddleware));
  const store = createStore(reducer, enhancer);
  const persistor = persistStore(store);
  sagaMiddleware.run(sagas);
  return { store, persistor };
}
