import Vue from 'vue';
import Vuex from 'vuex';

import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        role: '',
        socket: {
            socketClient: null,
            stompClient: null
        }
    },
    getters: {
        isAuthenticated: (state) => {
            return state.role != '';
        },
        isAdmin: (state) => {
            return state.role == 'ADMIN';
        },
        isMedic: (state) => {
            return state.role == 'MEDIC';
        }
    },
    mutations: {
        setRole: (state, value) => {
            state.role = value;
        },
        connectSocket: (state, cb) => {
            state.socket.socketClient = new SockJS("http://localhost:8080/api/notifications");
            state.socket.stompClient = Stomp.over(state.socket.socketClient);
            state.socket.stompClient.connect(
                {},
                () => {
                    state.socket.stompClient.subscribe("/monitoring", res => {
                        cb(res.body);
                    });
                },
                res => {
                    console.log(res);
                }
            );
        },
        disconnectSocket: (state) => {
            if (state.socket.stompClient) {
                state.socket.stompClient.disconnect();
            }
            state.socket.socketClient = null;
            state.socket.stompClient = null;
        }
    },
    actions: {
    }
})
