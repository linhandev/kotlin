// WITH_STDLIB
// WITH_REFLECT
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 330 -> sentence 330
 * PRIMARY LINKS: annotations, annotation-use-site-targets -> paragraph 330 -> sentence 330
 * NUMBER: 1
 * DESCRIPTION: @receiver: use-site target annotation on an extension function works normally at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.full.extensionReceiverParameter
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.functions

annotation class MyAnnotation
class MyClass {
    fun @receiver:MyAnnotation String.ext(): Int = this.length
}

fun box(): String {
    val instance = MyClass()
    if (instance.run { "hello".ext() } != 5) return "NOK: hello"
    if (instance.run { "".ext() } != 0) return "NOK: empty"
    if (instance.run { "ab".ext() } != 2) return "NOK: ab"

    val ext = MyClass::class.functions.singleOrNull { it.name == "ext" }
        ?: return "NOK: missing ext function"
    val receiver = ext.extensionReceiverParameter ?: return "NOK: missing extension receiver"
    if (receiver.findAnnotation<MyAnnotation>() == null) return "NOK: missing @receiver annotation"
    return "OK"
}
