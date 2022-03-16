Handlebars.registerHelper("fori", function(fromI, toI, options) {
    let itemsAsHtml = "";
    for (let i = fromI; i < toI; i++) {
        this["i"] = i;
        itemsAsHtml+=options.fn(this);
    }
    return itemsAsHtml;
});

Handlebars.registerHelper("more", function(a, b, options) {
    return a > b;
});

function getCsrf() {
    let metas = document.getElementsByTagName("meta");
    let token;
    for (let i = 0; i < metas.length; i++) {
        if (metas[i].name == "_csrf"){
            token = metas[i].content;
            break;
        }
    }
    return token;
}
