# mynotes
aws ec2 describe-instances --filters "Name=subnet-id,Values=**YourSubnetID**" --query 'Reservations[*].Instances[*].PrivateIpAddress' --output text

GET my_policy/_search

{

  "size": 0,

  "aggs": {

    "unique_policies": {

      "composite": {

        "sources": [

          {

            "policy_number": {

              "terms": {

                "field": "policy_number.keyword"

              }

            }

          },

          {

            "policy_holder_name": {

              "terms": {

                "field": "policy_holder_name.keyword"

              }

            }

          },

          {

            "dob": {

              "terms": {

                "field": "dob"

              }

            }

          },

          {

            "contact": {

              "terms": {

                "field": "contact.keyword"

              }

            }

          }

        ]

      }

    }

  }

}

