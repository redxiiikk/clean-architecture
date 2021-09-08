package hk.qingke.kotlin.usermanager.handler.adpater

import kotlin.reflect.KClass

interface ServiceFactory {
    fun <T : Any> getService(kClass: KClass<T>): T
}
