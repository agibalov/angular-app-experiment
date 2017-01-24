var done = arguments[0];
window
    .getAngularTestability(document.querySelector('outer-app'))
    .whenStable(done);
