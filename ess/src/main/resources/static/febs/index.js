layui.extend({
    febs: 'lay/modules/febs',
    validate: 'lay/modules/validate',
    echarts: 'lay/extends/echarts',
    echartsTheme: 'lay/extends/echartsTheme',
    datautil: 'lay/modules/datautil'
}).define(['febs', 'conf'], function (exports) {
    layui.febs.initPage();
    exports('index', {});
});