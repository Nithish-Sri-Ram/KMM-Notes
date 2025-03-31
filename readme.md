New to KMM and face the kapt error from the first day 

Here is the fix

Found solution in reddit - https://www.reddit.com/r/Kotlin/comments/ack2r6/problem_using_kapt_in_a_multiplatform_project/?rdt=37517

I had trouble finding an answer to this, even 5 months later. So thought i'd post it. There are answers for this but they are lodged amongst other junk and just not entirely clear. If you get one of these issues from your Kotlin gradle DSL

- gradle cannot find method kapt()

- kapt call gives this error: Type mismatch: inferred type is String but Action<KaptExtension> was expected

Make sure your gradle code has these things, in this order:

plugins {
kotlin("multiplatform")
kotlin("kapt")
}

kotlin {//Must be before dependencies block
android()
}

dependencies {//Kapt dependencies must be in top level block
"kapt"("your-dependency")
//Must use quoted "kapt" otherwise you get some other incorrect method with the same name -> org.gradle.api.Project.`kapt`
}