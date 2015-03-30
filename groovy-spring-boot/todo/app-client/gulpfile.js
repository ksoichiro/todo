var gulpFilter = require('gulp-filter'),
    gutil = require('gulp-util'),
    uglify = require('gulp-uglify'),
    bowerSrc = require('gulp-bower-src'),
    sourcemaps = require('gulp-sourcemaps'),
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

gulp.task('bower-files', function() {
    var filter = gulpFilter(["**/*.js", "!**/*.min.js"]);
    return bowerSrc()
        .pipe(sourcemaps.init())
        .pipe(filter)
        .pipe(uglify())
        .pipe(filter.restore())
        .pipe(sourcemaps.write("./"))
        .pipe(gulp.dest(paths.dest+'lib'));
});

gulp.task('build', ['minify-css', 'bower-files'], function() {});
