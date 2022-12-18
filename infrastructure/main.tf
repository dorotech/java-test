provider "aws" {
  region = "us-east-1"
}

terraform {
  backend "s3" {
    bucket  = "product.tfstates"
    key     = "terraform.tfstates"
    region  = "us-east-1"
    encrypt = true
  }
}
