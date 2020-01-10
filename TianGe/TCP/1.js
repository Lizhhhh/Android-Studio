class Observer {
	constructor() {
		Observer.prototype.__messages = {};
	}
	regist(type, fn) {
		if (typeof this.__messages[type] === 'undefined') {
      Observer.prototype.__messages = [fn];
		} else {
			Observer.prototype.__messages[type].push(fn);
		}
	}
	fire(type, args) {
		if (!Observer.prototype.__messages[type]) return;
		let events = {
			type,
			args: args || {}
		};
		let len = Observer.prototype.__messages[type].length;
		for (let i = 0; i < len; i++) {
			Observer.prototype.__messages[type][i].call(Observer, events);
		}
	}
	remove(type, fn) {
		if (Observer.prototype.__messages[type] instanceof Array) {
			let i = Observer.prototype.__messages[type].length - 1;
			for (; i >= 0; i--) {
				Observer.prototype.__messages[type] &&
					Observer.prototype.__messages[type].splice(i, 1);
			}
		}
	}
}

class A extends Observer {
	constructor() {
		super();
	}
}

class B extends Observer {
	constructor() {
		super();
	}
}

const a = new A();
const b = new B();
a.regist('SYN_SEND', str => console.log(str));
b.fire('SYN_SEND', 'Hello');

class Base {
	constructor() {}
	send(from, msg) {
		switch (from) {
			case 'C':
				this.toServer(msg);
				break;
			case 'S':
				this.toClient(msg);
				break;
		}
	}
	toServer(msg) {
		console.log('to server:', msg);
	}
	toClient(msg) {
		console.log('to client', msg);
	}
	ISN() {
		return Date.parse(new Date());
	}
}

class Client extends Base {
	constructor() {
		super();
	}
}

class Server extends Base {
	constructor() {
		super();
	}
}

class SYN extends Base {
	constructor() {
		super();
		return {
			SYN: 1,
			seq: this.ISN()
		};
	}
}
class ACK {
	constructor(seq) {
		return {
			ACK: 1,
			ack: Number(seq) + 1
		};
	}
}
class FIN extends Base {
	constructor() {
		super();
		return {
			FIN: 1,
			seq: this.ISN()
		};
	}
}
class SYN_ACK extends Base {
	constructor() {
		super();
		return {};
	}
}
