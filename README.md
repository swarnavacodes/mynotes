# mynotes
aws ec2 describe-instances --filters "Name=subnet-id,Values=**YourSubnetID**" --query 'Reservations[*].Instances[*].PrivateIpAddress' --output text

from feature 1
