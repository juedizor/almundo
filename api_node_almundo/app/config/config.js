var config = {
  production: {
    port: 8080,
  },
  default: {
    port: 3000
  }
}

exports.get = function get(env) {
  return config[env] || config.default;
}
