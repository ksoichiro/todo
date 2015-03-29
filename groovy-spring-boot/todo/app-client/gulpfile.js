var filter = require('gulp-filter'),
    cssmin = require('gulp-minify-css'),
    gulp = require('gulp');

var paths = {
    css: {
        files: ['src/css/*.css'],
        root: 'src/css'
    },
    dest: './dist/'
};

gulp.task('minify-css', function() {
    return gulp.src(paths.css.files)
        .pipe(cssmin({root: paths.css.root}))
        .pipe(gulp.dest(paths.dest + 'css'));
});

gulp.task('build', ['minify-css'], function() {});
