// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 344 -> sentence 344
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 344 -> sentence 344
 * NUMBER: 1
 * DESCRIPTION: precise type inference for a class with an annotation that has a KClass parameter
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.KClass

annotation class MyAnnotation(val cls: KClass<*>)

@MyAnnotation(String::class)
class MyClass(val x: Int)

fun case_1() {
    val instance = MyClass(42)
    instance checkType { check<MyClass>() }
    checkSubtype<MyClass>(instance)
}
