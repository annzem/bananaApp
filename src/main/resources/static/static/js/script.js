Handlebars.registerHelper("fori", function(fromI, toI, options) {
    let itemsAsHtml = "";
    for (let i = fromI; i < toI; i++) {
        this["i"] = i;
        itemsAsHtml+=options.fn(this);
    }
    return itemsAsHtml;
});
