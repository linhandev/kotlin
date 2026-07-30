// WITH_STDLIB
// WITH_REFLECT
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 337 -> sentence 337
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 337 -> sentence 337
 * NUMBER: 1
 * DESCRIPTION: annotation on an enum class and the enum class works normally at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.full.findAnnotation

annotation class MyAnnotation

@MyAnnotation
enum class MyEnum(val value: Int) {
    A(1),
    B(2);

    fun compute(): Int = value * 2
}

fun box(): String {
    if (MyEnum.A.compute() != 2) return "NOK: enum A"
    if (MyEnum.B.compute() != 4) return "NOK: enum B"
    if (MyEnum.valueOf("A") != MyEnum.A) return "NOK: valueOf"
    if (MyEnum.entries.size != 2) return "NOK: entries size"

    if (MyEnum::class.findAnnotation<MyAnnotation>() == null) return "NOK: missing enum annotation"
    return "OK"
}
