var gulpFilter = require('gulp-filter'),
    gutil = require('gulp-util'),
    uglify = require('gulp-uglify'),
    bowerSrc = require('gulp-bower-src'),
    sourcemaps = require('gulp-sourcemaps'),
    coffee = require('gulp-coffee'),
    less = require('gulp-less'),
    cssmin = require('gulp-minify-css'),
    gulp = require('gulp');

var paths = {
    less: {
        files: ['src/stylesheets/*.less'],
        root: 'src/stylesheets'
    },
    coffee: {
        files: ['src/javascripts/*.coffee'],
        root: 'src/javascripts'
    },
    dest: './dist/'
};

gulp.task('less', function() {
    return gulp.src(paths.less.files)
        .pipe(less())
        .pipe(cssmin({root: paths.less.root}))
        .pipe(gulp.dest(paths.dest + 'css'));
});

gulp.task('coffee', function() {
    return gulp.src(paths.coffee.files)
        .pipe(coffee())
        .pipe(uglify())
        .pipe(gulp.dest(paths.dest + 'js'));
});

gulp.task('bower-files', function() {
    var filter = gulpFilter(["**/*.js", "!**/*.min.js"]);
    return bowerSrc()
        .pipe(sourcemaps.init())
        .pipe(filter)
        .pipe(uglify())
        .pipe(filter.restore())
        .pipe(sourcemaps.write("./"))
        .pipe(gulp.dest(paths.dest + 'lib'));
});

gulp.task('build', ['less', 'coffee', 'bower-files'], function() {});
