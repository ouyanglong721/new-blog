const g = {}

g.baseUrl = "http://127.0.0.1:8081/";


g.clearParams = function(datas){
	var v_data ={};
	for(var a in datas){
		if (datas[a] instanceof Array) {
			v_data[a]=[];
		} else {
			v_data[a] = '';
		}
	}
	return v_data;
};

export default g