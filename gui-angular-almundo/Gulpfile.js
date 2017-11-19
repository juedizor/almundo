var gulp = require('gulp');
var connect = require('gulp-connect');
var historyApiFallback = require('connect-history-api-fallback');
var webserver = require('gulp-webserver');
var stylus = require('gulp-stylus');
var nib = require('nib');
var jshint = require('gulp-jshint');
var stylish = require('jshint-stylish');
var opn = require('opn');
var inject = require('gulp-inject');
var wiredep = require('wiredep').stream;

var gulpif = require('gulp-if'),
  minifycss = require('gulp-minify-css'),
  useref = require('gulp-useref'),
  uglify = require('gulp-uglify');

gulp.task('compress', function() {
  gulp.src('./app/index.html')
    .pipe(useref())
    .pipe(gulpif('*.js', uglify({
      mangle: false
    })))
    .pipe(gulpif('*.css', minifycss()))
    .pipe(gulp.dest('./dist'));
});

gulp.task('copy', function() {
  gulp.src('./app/index.html')
    .pipe(useref())
    .pipe(gulp.dest('./dist'));
  gulp.src('./app/views/**/hoteles/*')
    .pipe(useref())
    .pipe(gulp.dest('./dist/views'));
  gulp.src('./app/templates/**/app/*')
    .pipe(useref())
    .pipe(gulp.dest('./dist/templates'));
  gulp.src('./app/data/**/assets/**/icons/**/filters/*')
    .pipe(useref())
    .pipe(gulp.dest('./dist/data/'));
  gulp.src('./app/data/**/assets/**/images/*')
    .pipe(useref())
    .pipe(gulp.dest('./dist/data/'));
});

// production server
gulp.task('prodServer', function() {
  gulp.src('dist')
    .pipe(webserver({
      livereload: true,
      directoryListing: false,
      open: true,
    }));
});

var options = {
  bowerJson: require('./bower.json'),
  directory: './app/lib/'
}

// develop server
gulp.task('webserver', function() {
  gulp.src('./app')
    .pipe(webserver({
      livereload: true,
      directoryListing: false,
      open: true,
    }));
});

// search error and show that
gulp.task('jshint', function() {
  return gulp.src('./app/scripts/**/*.js')
    .pipe(jshint('.jshintrc'))
    .pipe(jshint.reporter('jshint-stylish'))
    .pipe(jshint.reporter('fail'));
});

// Reload the web browser when there is change in html
gulp.task('html', function() {
  gulp.src('./app/**/*.html')
    .pipe(connect.reload());
});

// inject library installed with bower
gulp.task('wiredep', function() {
  return gulp.src('./app/index.html')
    .pipe(wiredep(options))
    .pipe(gulp.dest('./app'));
});

gulp.task('inject', function() {
  var sources = gulp.src(['./app/scripts/**/*.js', './app/stylesheets/**/*.css']);
  return gulp.src('index.html', {
      cwd: './app'
    })
    .pipe(inject(sources, {
      read: false,
      ignorePath: '/app'
    }))
    .pipe(gulp.dest('./app'));
});

gulp.task('watch', function() {
  gulp.watch(['./app/**/*.html'], ['html']);
  gulp.watch(['./app/scripts/**/*.js', './Gulpfile.js'], ['jshint', 'inject']);
  gulp.watch(['./bower.json'], ['wiredep']);
});

gulp.task('default', ['webserver', 'wiredep', 'watch', 'jshint', 'inject']);

gulp.task('build', ['compress', 'copy']);
