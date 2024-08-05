 //定义图表对象
 var myChart1 = null;
 var myChart2 = null;
 var myChart3 = null;
 var myChart4 = null;
 var myChart5 = null;
 var myChart6 = null;
 var tree;
 function init(formWz){
	$(document).ready(function(){
		$.each($(".mybutton"),function(key,values){
			setTimeout(function(){
				$(values).toggleClass("active");
				$(values).siblings().toggleClass("open");
				$(values).siblings().children(".divcompont").toggleClass("open");
				
				if($('.xmbtn').hasClass('active')){
					$('.wz').css({
						"overflow":"visible"
					});
				}else{
					$('.wz').css({
						"overflow":"hidden"
					});
				}
				
			},key*500);
		});
		///初始化树形菜单
		setTimeout(displayTree,500 , formWz);
		///
		layui.form.render();
		
	});
	
	$(".mybutton").click(function () {
		$(this).toggleClass("active");
		$(this).siblings().toggleClass("open");
		$(this).siblings().children(".divcompont").toggleClass("open");
		
		if($('.xmbtn').hasClass('active')){
			setTimeout(function(){
				$('.wz').css({
					"overflow":"visible"
				});
			},500);
			
		}else{
			$('.wz').css({
				"overflow":"hidden"
			});
		}
		
	});
 }
 /**
 *	房屋类别统计
 */
 function industryBar(fwtjlbmc,fwtjlbsj){	
	myChart1 =  echarts.init(document.getElementById('industryBar'));	// 基于准备好的dom，初始化echarts实例
	var option = {
		title: {
			text: '房屋类别统计',
			left: 'center',
			textStyle:{color:'#fff'},
			top:'4%',
		},
		tooltip: {
			trigger: 'axis',
			axisPointer: {            // 坐标轴指示器，坐标轴触发有效
				type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		grid: {
			left: '2%',
			right: '4%',
			top:'20%',
			bottom: '3%',
			containLabel: true
		},
		xAxis: [
			{
				type: 'category',
				data: fwtjlbmc,
				axisTick: {
					alignWithLabel: true
				},
				axisLabel: {		//x轴文字颜色
					color: '#fff'
				},
				axisLine: {		    //x轴线的颜色
					lineStyle:{
						 color: ['#fff'],
						 width: 1,
						 type: 'solid',
						 opacity: 0.1
					}
				}
			}
		],
		yAxis: [
			{
				type: 'value',
				axisLabel: {		//y轴文字颜色
					color: '#fff'
				},
				axisLine: {		    //y轴线的颜色
					lineStyle:{
						 color: ['#fff'],
						 width: 1,
						 type: 'solid',
						 opacity: 0.1
					}
				},
				splitLine: {        //网格样式
					show: true,
					lineStyle:{
						 color: 'rgba(255,255,255,0.1)',
						 width: 1,
						 type: 'dashed'
					}
				}
			}
		],
		series: [
			{
				name: '数量',
				type: 'bar',
				barWidth: '50%',
				itemStyle: {
					normal: {
						color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
							offset: 0,
							color: '#248ff7'
						}, {
							offset: 1,
							color: '#6851f1'
						}]),
					barBorderRadius: 11,
				}
			  },
				data:fwtjlbsj 
			}
		]
	};
	myChart1.setOption(option,true);	    // 使用刚指定的配置项和数据显示图表。
   var app = {
	currentIndex: -1,
	
  };
  /*
 setInterval(function () {
	var dataLen = option.series[0].data.length;

	// 取消之前高亮的图形
	myChart1.dispatchAction({
	  type: 'downplay',
	  seriesIndex: 0,
	  dataIndex: app.currentIndex
	});
	app.currentIndex = (app.currentIndex + 1) % dataLen;
	//console.log(app.currentIndex);
	// 高亮当前图形
	myChart1.dispatchAction({
	  type: 'highlight',
	  seriesIndex: 0,
	  dataIndex: app.currentIndex,
	});
	// 显示 tooltip
	myChart1.dispatchAction({
	  type: 'showTip',
	  seriesIndex: 0,
	  dataIndex: app.currentIndex
	});
  }, 1000);
  */
  	//$(document).ready(function(){
	//　　myChart1.resize();  
	//})
	window.addEventListener("resize", function () {
	　　myChart1.resize();  
	});
}

/**
*建筑面积
*/
function changeChart(jzmj){
	myChart2 =  echarts.init(document.getElementById('changeLine'));
	var option = {
	  title: {
			text: '建筑面积',
			left: 'center',
			textStyle: {color: '#fff'},
			top:'4%',
	  },
	  tooltip: {
		trigger: 'axis',
		axisPointer: {
		  type: 'shadow'
		}
	  },
	  legend: {},
	  grid: {
		left: '3%',
		right: '8%',
		bottom: '5%',
		containLabel: true
	  },
	  xAxis: {
		type: 'value',
		axisLabel: {		//x轴文字颜色
			color: '#fff'
		},
		axisLine: {		    //x轴线的颜色
			lineStyle:{
				color: ['#fff'],
				width: 1,
				type: 'solid',
				opacity: 0.1
			}
		},
	  },
	  yAxis: {
		type: 'category',
		axisLabel: {		//y轴文字颜色
			color: '#fff'
		},
		axisLine: {		    //y轴线的颜色
			lineStyle:{
				color: ['#fff'],
				width: 1,
				type: 'solid',
				opacity: 0.1
			}
		},
		data: ['面积']
	  },
	  series: [
		{
		  name: '',
		  type: 'bar',
		  stack: 'total',
		  itemStyle: {
			normal: {
			label:{
				show: true,
				formatter: '{c}(㎡)'
			},
			barBorderRadius: 11,
			}
		  },
		  emphasis: {
			focus: 'series'
		  },
		  data: jzmj,
		  color:['#315f97']
		}
	  ]
	};
	myChart2.setOption(option,true);	    // 使用刚指定的配置项和数据显示图表。
	//$(document).ready(function(){
	//　　myChart2.resize();  
	//})
	window.addEventListener("resize", function () {
	　　myChart2.resize();  
	});
}
 /**
 *权利类型
 */
 function ageTrendPie(qllx){
	myChart3 = echarts.init(document.getElementById('agePie'));
	var option = {
		title: {
			text: '权利类型',
			left: 'center',
			textStyle: {color: '#fff'},
			top:'4%',
		},
		tooltip: {
			trigger: 'item',
			formatter: '{a} <br/>{b}: {c} ({d}%)'
		},
		grid: {
			left: '3%',
			right: '4%',
			top:'35%',
			bottom: '3%'
		},
		series: [
			{
				name: '占比',
				type: 'pie',
				radius: ['50%', '70%'],
				avoidLabelOverlap: true,
				label: {
					show: false,
					position: 'center'
				},
				emphasis: {
					label: {
						show: true,
						fontSize: '30',
						fontWeight: 'bold'
					}
				},
				labelLine: {
					show: false
				},
				itemStyle: {
					normal: {
						label:{
							show: true,
							color: '#ffffff',
							position: 'middle',
							formatter: '{b}数量 \n {c} \n ({d}%)'
						}
					}
				},
				color:['#ff0000','#315f97'],
				data: qllx
			}
		]
	};
	myChart3.setOption(option,true);	    // 使用刚指定的配置项和数据显示图表。
	//$(document).ready(function(){
	//　　myChart3.resize();  
	//})
	window.addEventListener("resize", function () {
	　　myChart3.resize();  
	});
 }
 
 /**
 *房产数、产权人数、居住人数、房屋间数
 */
 function  skillAcquisitionChart(visits,cost){
	//var cost = [69, 78, 60,34]//本次搜索数量
	var totalCost = [100,100, 100,100]//比例综合
	//var visits = [ 100,100, 100, 100]//各类总数
	var grade = ['房产数','产权人数','居住人数','房屋间数' ]
	var myColor = ['#21BF57','#56D0E3','#1089E7','#F8B448'];
	var data = {
		grade: grade,
		cost: cost,
		totalCost: totalCost,
		visits: visits
	};
	var option = {
		title: {
			top: '4%',
			left: 'center',
			text: '数量占比',
			textStyle: {
				align: 'center',
				color:'#fff'
			}
		},
		grid: {
			left: '130',
			right: '100',
			bottom: '20'
		},
		xAxis: {
			show: false,
		},
		yAxis: {
			type: 'category',
			axisLabel: {
				margin:30,
				show: true,
				color: '#4DCEF8',
				fontSize: 14
			},
			axisTick: {
				show: false,
			},
			axisLine: {
				show: false,
			},
			data: data.grade
		},
		series: [{//外层边框
			type: 'bar',
			barGap: '-65%',
			label: {
				normal: {
					show: true,
					position: 'right',
					color: '#fff',
					fontSize: 14,
					formatter: 
					function(param) {
						return data.visits[param.dataIndex];
					},
				}
			},
			barWidth: '30%',
			itemStyle: {
				normal: {
					borderColor: '#4DCEF8',
					borderWidth: 2,
					barBorderRadius: 15,
					color: 'rgba(102, 102, 102,0)'
				},
			},
			z: 1,
			data: data.totalCost,
			// data: da
		}, {//柱形图占比
			type: 'bar',
			barGap: '-85%',
			barWidth: '21%',
			itemStyle: {
				 normal: {
					barBorderRadius: 16,
					color: function(params) {
						var num = myColor.length;
						return myColor[params.dataIndex % num]
					},
				}
			},
			max: 1,
			label: {
				normal: {
					show: true,
					position: 'inside',
					formatter: '{c}%'
				}
			},
			labelLine: {
				show: true,
			},
			z: 2,
			data: data.cost,
		}]
	}
	myChart4 = echarts.init(document.getElementById('skillAcquisitionBar'));
	myChart4.setOption(option,true);
	//$(document).ready(function(){
	//　　myChart4.resize();  
	//})
	window.addEventListener("resize", function () {
	　　myChart4.resize();  
	});
}

/**
*土地使用面积
*/
function playCounts(tdsymj){
	var option = {
	  color:['#1d9dff'],
	  title: [
		{
		    text: '土地使用面积',
			left: 'center',
			top:'4%',
			textStyle: {
				color: '#fff'
			}
		}
	  ],
	  polar: {
		radius: [20, '50%']
	  },
	  grid: {
		top: '10%',
		left: '3%',
		right: '8%',
		bottom: '5%',
		containLabel: true
	  },
	  angleAxis: {
		max: 351410,
		axisLabel: {		//y轴文字颜色
			color: '#fff'
		},
		axisLine: {		    //y轴线的颜色
			lineStyle:{
				color: ['#fff'],
				width: 1,
				type: 'solid',
				opacity: 0.1
			}
		},
		startAngle: 90
	  },
	  radiusAxis: {
		type: 'category',
		axisLabel: {		//y轴文字颜色
			color: '#fff'
		},
		axisLine: {		    //y轴线的颜色
			lineStyle:{
				color: ['#fff'],
				width: 1,
				type: 'solid',
				opacity: 0.1
			}
		},
		data: ['']
	  },
	  tooltip: {},
	  series: {
		color:['#1d9dff'],
		type: 'bar',
		data: tdsymj,
		coordinateSystem: 'polar',
		label: {
			color: '#ffffff',
			show: true,
			position: 'middle',
			formatter: '{c}(㎡)'
		}
	  }
	};
	myChart5 = echarts.init(document.getElementById('playCounts'), null, {
      renderer: 'canvas',
      useDirtyRect: false
    });
	myChart5.setOption(option,true);
	//$(document).ready(function(){
	　　//myChart5.resize();  
	//})
	window.addEventListener("resize", function () {
	　　myChart5.resize();  
	});
}    

/**
*	权利性质
*/
function roseChart(qlxz){
	var option = {
		color:['#1d9dff','#86c9f4'],
		title: {
			text: '权利性质',
			left: 'center',
			top:'4%',
			textStyle: {
				color: '#fff'
			}
		},
		tooltip: {
			trigger: 'item',
			formatter: '{a} <br/>{b} : {c} ({d}%)'
		},
		legend: {
			left: 'center',
			top: 'bottom',
			data: ['国有', '集体'],
			textStyle: {
				color: '#fff'
			}
		},
		series: [
			{
				name: '半径模式',
				type: 'pie',
				radius: [15, 50],
				center: ['50%', '50%'],
				roseType: 'radius',
				label: {
					show: true
				},
				emphasis: {
					label: {
						show: true,
						fontSize: '24',
						//fontWeight: 'bold'
					}
				},
				labelLine: {
					show: false
				},
				itemStyle: {
					normal: {
						label:{
							color: '#ffffff',
							position: 'middle',
							show: true,
							formatter: '{b}数量 \n {c} \n ({d}%)'
						}
					}
				},
				data: qlxz
			}
		]
	};
	myChart6 = echarts.init(document.getElementById('roseChart'));
	myChart6.setOption(option);
	//$(document).ready(function(){
	//　　myChart6.resize();  
	//})
	window.addEventListener("resize", function () {
	　　myChart6.resize();  
	});
}

function displayTree(formWz){
	  layui.use(['tree', 'util'], function(){
	  var layer = layui.layer
	  ,util = layui.util
	  //模拟数据
	  ,data = formWz;
	  tree = layui.tree;
	  //基本演示
	  tree.render({
		elem: '#gc'
		,data: data
		,showCheckbox: true  //是否显示复选框
		,id: 'shggc'
		,click: function(obj){
			treeMenu();
		}
	  });
	  
	  $('.layui-icon').click(treeMenu);
	  
	  function treeMenu(){
		  setTimeout(function(){
				var treeHeight = $('.layui-tree').height();
				var treeTop = $(".demo-tree-more").offset().top;
				var wHeight = window.innerHeight;//$(window).height();
				if(treeHeight + treeTop > wHeight*0.9){
					$(".demo-tree-more").css({
						"height": wHeight*0.9 - treeTop + "px",
						"overflow-y": "scroll"
					});
				}else{
					$(".demo-tree-more").css({
						"height": treeHeight + "px",
						"overflow-y": "hidden"
					});
				}
		  },200);
	  }
	});
}