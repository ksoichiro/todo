var gulpFilter = require('gulp-filter'),
    gutil = require('gulp-util'),
    uglify = require('gulp-uglify'),
    bowerSrc = require('gulp-bower-src'),
    sourcemaps = require('gulp-sourcemaps'),
    less = require('gulp-less'),
    cssmin = require('gulp-minify-css'),
    gulp = require('gulp');

var paths = {
    less: {
        files: ['src/stylesheets/*.less'],
        root: 'src/stylesheets'
    },
    dest: './dist/'
};

gulp.task('less', function() {
    return gulp.src(paths.less.files)
        .pipe(less())
        .pipe(cssmin({root: paths.less.root}))
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

gulp.task('build', ['less', 'bower-files'], function() {});
