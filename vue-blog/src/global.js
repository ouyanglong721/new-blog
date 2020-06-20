const g = {}

g.baseUrl = "http://47.244.18.233:8088/";


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