//服务层
app.service('contentCategoryService',function($http){

	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../contentCategory/findAll.shtml');
	}
	//分页
	this.findPage=function(page,size){
		return $http.get('../contentCategory/findPage.shtml?page='+page+'&size='+size);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get('../contentCategory/findOne.shtml?id='+id);
	}
	//增加
	this.add=function(entity){
		return  $http.post('../contentCategory/add.shtml',entity );
	}
	//修改
	this.update=function(entity){
		return  $http.post('../contentCategory/update.shtml',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get('../contentCategory/delete.shtml?ids='+ids);
	}
	//搜索
	this.search=function(page,size,searchEntity){
		return $http.post('../contentCategory/search.shtml?page='+page+"&size="+size, searchEntity);
	}
});
