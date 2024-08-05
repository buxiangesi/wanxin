/*大屏*/
var myechart,myechart1,myechart2,myechart3,myechart4,mapechart;

//自调用函数
(function () {
    // 1、页面一加载就要知道页面宽度计算
    var setFont = function () {
        // 因为要定义变量可能和别的变量相互冲突，污染，所有用自调用函数
        var html = document.documentElement;// 获取html
        // 获取宽度
        var width = html.clientWidth;

        // 判断
        if (width < 1024) width = 1024
        if (width > 1920) width = 1920
        // 设置html的基准值
        var fontSize = width / 80 + 'px';
        // 设置给html
        html.style.fontSize = fontSize;
    }
    setFont();
    // 2、页面改变的时候也需要设置
    // 尺寸改变事件
    window.onresize = function () {
        setFont();
    }
})();

(function () {
    //事件委托
    $('.monitor').on('click', ' a', function () {
        //点击当前的a 加类名 active  他的兄弟删除类名
        $(this).addClass('active').siblings().removeClass('active');
        //获取一一对应的下标 
        var index = $(this).index();
        //选取content 然后狗日对应下标的 显示   当前的兄弟.content隐藏
        $('.content').eq(index).show().siblings('.content').hide();
    });
    //滚动
    //原理：把marquee下面的子盒子都复制一遍 加入到marquee中
    //      然后动画向上滚动，滚动到一半重新开始滚动
    //因为选取的是两个marquee  所以要遍历
    $('.monitor .marquee').each(function (index, dom) {
        //将每个 的所有子级都复制一遍
        var rows = $(dom).children().clone();
        //再将新的到的加入原来的
        $(dom).append(rows);
    });

})();
	
	
//房产分布统计
function fcfbtj(chartData) {
	
    myechart = echarts.init($('.pie')[0]);
    option = {
        // 控制提示
        tooltip: {
            // 非轴图形，使用item的意思是放到数据对应图形上触发提示
            trigger: 'item',
            // 格式化提示内容：
            // a 代表图表名称 b 代表数据名称 c 代表数据  d代表  当前数据/总数据的比例
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        // 控制图表
        series: [
            {
                // 图表名称
                name: '片区',
                // 图表类型
                type: 'pie',
                // 南丁格尔玫瑰图 有两个圆  内圆半径10%  外圆半径70%
                // 百分比基于  图表DOM容器的半径
                radius: ['10%', '70%'],
                // 图表中心位置 left 50%  top 50% 距离图表DOM容器
                center: ['50%', '50%'],
                // 半径模式，另外一种是 area 面积模式
                roseType: 'radius',
                // 数据集 value 数据的值 name 数据的名称
                data: chartData.fcfbtj,
                //文字调整
                label: {
                    fontSize: 10
                },
                //引导线
                labelLine: {
                    length: 8,
                    length2: 10
                }
            }
        ],
        color: ['#006cff', '#60cda0', '#ed8884', '#1d9dff']
    };
    myechart.setOption(option);
};



//古城胡同房产排行
function htph(chartData) {
    // 中间省略的数据  准备三项
    var item = {
        name: '',
        value: 400,
        // 柱子颜色
        itemStyle: {
            color: '#254065'
        },
        // 鼠标经过柱子颜色
        emphasis: {
            itemStyle: {
                color: '#254065'
            }
        },
        // 工具提示隐藏
        tooltip: {
            extraCssText: 'opacity:0'
        }
    };
    option = {
        // 工具提示
        tooltip: {
            // 触发类型  经过轴触发axis  经过轴触发item
            trigger: 'item',
            // 轴触发提示才有效
            axisPointer: {
                // 默认为直线，可选为：'line' 线效果 | 'shadow' 阴影效果       
                type: 'shadow'
            }
        },
        // 图表边界控制
        grid: {
            // 距离 上右下左 的距离
            left: '0',
            right: '3%',
            bottom: '3%',
            top: '5%',
            // 大小是否包含文本【类似于boxsizing】
            containLabel: true,
            //显示边框
            show: true,
            //边框颜色
            borderColor: 'rgba(0, 240, 255, 0.3)'
        },
        // 控制x轴
        xAxis: [
            {
                // 使用类目，必须有data属性
                type: 'category',
                // 使用 data 中的数据设为刻度文字
                data: chartData.htphmc,
                // 刻度设置
                axisTick: {
                    // true意思：图形在刻度中间
                    // false意思：图形在刻度之间
                    alignWithLabel: false,
                    show: false
                },
                //文字
                axisLabel: {
                    color: '#4c9bfd'
                }
            }
        ],
        // 控制y轴
        yAxis: [
            {
                // 使用数据的值设为刻度文字
                type: 'value',
                axisTick: {
                    // true意思：图形在刻度中间
                    // false意思：图形在刻度之间
                    alignWithLabel: false,
                    show: false
                },
                //文字
                axisLabel: {
                    color: '#4c9bfd'
                },
                splitLine: {
                    lineStyle: {
                        color: 'rgba(0, 240, 255, 0.3)'
                    }
                },
            }
        ],
        // 控制x轴
        series: [

            {
                // series配置
                // 颜色
                itemStyle: {
                    // 提供的工具函数生成渐变颜色
                    color: new echarts.graphic.LinearGradient(
                        // (x1,y2) 点到点 (x2,y2) 之间进行渐变
                        0, 0, 0, 1,
                        [
                            { offset: 0, color: '#00fffb' }, // 0 起始颜色
                            { offset: 1, color: '#0061ce' }  // 1 结束颜色
                        ]
                    )
                },
                // 图表数据名称
                name: '户数统计',
                // 图表类型
                type: 'bar',
                // 柱子宽度
                barWidth: '60%',
                // 数据
                data: chartData.htphsj
            }
        ]
    };
    myechart1 = echarts.init($('.users .bar')[0]);
    myechart1.setOption(option);
};

//房屋类别统计
function fclbtj(chartData) {
    var option = {
        //鼠标提示工具
        tooltip: {
            trigger: 'axis'
        },
        xAxis: {
            // 类目类型                                  
            type: 'category',
            // x轴刻度文字                                  
            data: chartData.fclbmc,
            axisTick: {
                show: false//去除刻度线
            },
            axisLabel: {
                color: '#4c9bfd'//文本颜色
            },
            axisLine: {
                show: false//去除轴线  
            },
            boundaryGap: false//去除轴内间距
        },
        yAxis: {
            // 数据作为刻度文字                                  
            type: 'value',
            axisTick: {
                show: false//去除刻度线
            },
            axisLabel: {
                color: '#4c9bfd'//文本颜色
            },
            axisLine: {
                show: false//去除轴线  
            },
            boundaryGap: false//去除轴内间距
        },
        //图例组件
        legend: {
            textStyle: {
                color: '#4c9bfd' // 图例文字颜色

            },
            right: '10%'//距离右边10%
        },
        // 设置网格样式
        grid: {
            show: true,// 显示边框
            top: '20%',
            left: '3%',
            right: '4%',
            bottom: '3%',
            borderColor: '#012f4a',// 边框颜色
            containLabel: true // 包含刻度文字在内
        },
        series: [{
            name: '数量',
            // 数据                                  
            data: chartData.fclbscsj,
            // 图表类型                                  
            type: 'line',
            // 圆滑连接                                  
            smooth: true,
            itemStyle: {
                color: '#00f2f1'  // 线颜色
            }
        }
		]
    };
    myechart2 = echarts.init($('.line')[0]);
    myechart2.setOption(option);

    //点击效果
    var data = {
        year: [
            chartData.fclbscsj
        ],
        quarter: [
            chartData.fclbgcsj
        ],
        month: [
            chartData.fclblcsj
        ],
 		day: [
            chartData.fclbjcsj
        ],
 		mm: [
            chartData.fclbjuncsj
        ]
    }
    $('.sales ').on('click', '.caption a', function () {
        $(this).addClass('active').siblings('a').removeClass('active');
        //option series   data
        //获取自定义属性值
        var key = $(this).attr('data-type');
        //取出对应的值
        key = data[key];
        //将值设置到 图表中
        option.series[0].data = key[0];
        //再次调用才能在页面显示
        myechart2.setOption(option);
    });
    //定时器
    var index = 0;
    var timer = setInterval(function () {
        index++;
        if (index > 5) {
            index = 0;
        };
        $('.sales .caption a').eq(index).click();
    }, 2000);
};

//古城人流量态势图

(function () {
	let airData = [
		{name:'西北片区',value:[122,30,2]},
		{name:'西南片区',value:[172,50]},
		{name:'东南片区',value:[32,40]},
		{name:'东北片区',value:[16,20]}
	];
    mapechart = echarts.init($('.map .geo')[0]);
    mapechart.setOption({
	    //backgroundColor: '#080a20',
		title: {
			left: 'left',
			textStyle: {
				color: '#fff'
			}
		},
		tooltip: {
			show:true,
			trigger: 'item',
			alwaysShowContent:false,
			backgroundColor: '#0C121C',
			borderColor: 'rgba(0,0,0,0.16);',
			hideDelay:100,
			triggerOn:'mousemove',
			enterable:true,
			textStyle:{
				color:'#DADADA',
				fontSize:'12',
				width:20,
				height:30,
				overflow:'break'
			},
			formatter(params){
				return `房产总数：${params.data.value[0]}</br>建筑面积：${params.data.value[1]}`;
			},
			showDelay:100
		},
		//renderAsImage:"./images/bg3.png",
		legend: {
			orient: 'vertical',
			top: 'bottom',
			left: 'right',
			textStyle: {
				color: '#fff'
			},
			selectedMode: 'single'
		},
        series: [
		{
            name: '山海关古城片区分布图',
			data: airData,
            type: 'map',
            roam: false, // 缩放和漫游
            map: 'shggc',   //指定刚才注册的地图
            //aspectScale: 1,    //宽高比，如果不指定，实例化出来的地图会偏瘦或者偏矮
            //zoom: 1,
            label: {
                    show: true,
                    color: '#BBC0CA',
            },
            emphasis: {   //鼠标浮动的时候，标注和各个地图区域的交互渲染设置
                    label: {
                        show: true,
                        // fontSize: 16,
                        color: '#39E639',
                    },
                    itemStyle: {
						//areaColor: '#0b1c2d'
						normal: {
							color: '#ffeb7b'
						}
                    },
					
            },
			itemStyle: {
				normal: {
					areaColor: 'rgba(2,37,101,.5)',
					borderColor: 'rgba(112,187,252,.5)'
				},
				emphasis: {
					areaColor: 'rgba(2,37,101,.8)'
				}
			},
            // 文本位置修正
            textFixed: {
                    Alaska: [20, -20],
            },
        }
		],
		
		graphic: [
			{
				type: 'image', // 图形元素类型
				right: 'center', // 根据父元素进行定位 （居中）
				bottom: '0%', // 根据父元素进行定位   （0%）, 如果bottom的值是 0，也可以删除该bottom属性值。
				z: 0,  // 层叠
				bounding: 'all', // 决定此图形元素在定位时，对自身的包围盒计算方式
				style: {
					image: '../DataView/images/bg3.png', // 这里一定要注意、注意，必须是https开头的图片路径地址
					//image: 'th:@{/DataView/images/bg3.png}', // 这里一定要注意、注意，必须是https开头的图片路径地址
					width: $(".geo").width(),
					height:$(".geo").height()
				}
			}
		],
		
    });
})();


//权力类型
function qllx(chartData) {
	myechart3 = echarts.init(document.getElementById('qllxPie'));
	var option = {
		title:{
			//1.标题居中
			//left的值为'left', 'center', 'right'
			left:'left',
			//默认为10
			//2.主副标题之间的间距
			//itemGap:20,
			//3.标题文本样式   
			text:'权利类型',
			textStyle:{
				//文字颜色
				color:'#fff',
				//字体风格,'normal','italic','oblique'
				fontStyle:'normal',
				//字体粗细 'normal','bold','bolder','lighter',100 | 200 | 300 | 400...
				fontWeight:'normal',
				//字体系列
				fontFamily:'微软雅黑',
				//字体大小
		　　　　 fontSize:18
			}
		},
		tooltip: {
			trigger: 'item',
			formatter: '{a} <br/>{b}: {c} ({d}%)'
		},
		grid: {
			left: '3%',
			right: '4%',
			top:'45%',
			bottom: '3%'
		},
		series: [
			{
				name: '权利类型',
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
						fontSize: '18',
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
							formatter: '{b} \n ({d}%)'
						}
					}
				},
				color:['#1d9dff','#ff0000'],
				data: chartData.qllx
			}
		]
	};
	myechart3.setOption(option,true);	    // 使用刚指定的配置项和数据显示图表。
 };

//权力性质
function qlxz(chartData) {
	myechart4 = echarts.init(document.getElementById('qlxzPie'), 'walden')

	option4 = {
		title:{
			//1.标题居中
			//left的值为'left', 'center', 'right'
			left:'left',
			//默认为10
			//2.主副标题之间的间距
			//itemGap:20,
			//3.标题文本样式   
			text:'权利性质',
			textStyle:{
				//文字颜色
				color:'#fff',
				//字体风格,'normal','italic','oblique'
				fontStyle:'normal',
				//字体粗细 'normal','bold','bolder','lighter',100 | 200 | 300 | 400...
				fontWeight:'normal',
				//字体系列
				fontFamily:'微软雅黑',
				//字体大小
		　　　　 fontSize:18
			}
		},
		tooltip: {
			trigger: 'item',
			formatter: '{a} <br/>{b}: {c}'
		},
		grid: {
			left: '15%',
			right: '10%',
			top:'25%',
			bottom: '15%'
		},
		xAxis: {
			type: 'category',
			data: chartData.qlxzmc,
			axisTick: {
                show: false//去除刻度线
            },
            axisLabel: {
                color: '#4c9bfd'//文本颜色
            },
            axisLine: {
                show: false//去除轴线  
            },
            //boundaryGap: false//去除轴内间距
		},
		yAxis: {
			type: 'value',
			axisTick: {
                show: false//去除刻度线
            },
            axisLabel: {
                color: '#4c9bfd'//文本颜色
            },
            axisLine: {
                show: false//去除轴线  
            },
            //boundaryGap: false//去除轴内间距
		},
		series: [
		{
			name: '权利性质',
			type: 'bar',
			label: {
				show: false,
				position: 'center'
			},
			emphasis: {
				label: {
					show: true,
					fontSize: '18',
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
						formatter: '{c}'
					}
				}
			},
			color:['#10606b','#ff0000'],
			//data: [999,1548]
			data: chartData.qlxz
		}
		]
	}
	// 使用刚指定的配置项和数据显示图表。
	myechart4.setOption(option4)
};

(function () {
    axios.get(prefix + '/getChartData')
				  .then(function (response) {
					  fcfbtj(response.data);
					  htph(response.data);
					  fclbtj(response.data);
					  qllx(response.data);
					  qlxz(response.data);
				  })

})();

/*
$(document).ready(function(){
	myechart.resize();
	myechart1.resize();
	myechart2.resize();
	myechart3.resize();  
	myechart4.resize(); 
	mapechart.resize();
})
*/
window.addEventListener("resize", function () {
	myechart.resize();
	myechart1.resize();
	myechart2.resize();
	myechart3.resize();  
	myechart4.resize(); 
	mapechart.resize();
});
