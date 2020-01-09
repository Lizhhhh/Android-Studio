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
  ISN(){
    return Date.parse(new Date());
  }
}
class Client extends Base {
  constructor(){
    super();
    console.log(this.ISN());
  }
}
class Server extends Base {
  constructor(){
    super();
  }
}
const c = new Client();