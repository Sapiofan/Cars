import React from 'react'
import styles from './Car.module.css'
import { DescriptionIcon } from './DescriptionIcon/DescriptionIcon'
import { ReactComponent as CarTypeIcon } from '../../media/car-icon.svg'
import { ReactComponent as FuelIcon } from '../../media/fuel-icon.svg'
import { ReactComponent as EngineIcon } from '../../media/engine-icon.svg'
import { ReactComponent as PersonIcon } from '../../media/person-icon.svg'
import { ReactComponent as TransmissionIcon } from '../../media/transmission-icon.svg'
import { ReactComponent as LitreIcon } from '../../media/litre-icon.svg'
import { useNavigate } from 'react-router-dom'
import { URL } from '../../globalConstants'

type CarProps = {
    image: string
    name: string
    price: number
    deposit: number
    type: string
    year: number
    speed: number
    engine_speed: number
    gearbox: string
    seats: number
    fuel_consumption: number
    fuel_type: string
    contracts: any[]
    pledge: string
    brand: string
    onModalOpen: () => void
    onCarBook: (car: any) => void
}
export const Car = (props: CarProps) => {
    const {
        image,
        name,
        price,
        brand,
        type,
        year,
        speed,
        engine_speed,
        gearbox,
        seats,
        fuel_consumption,
        fuel_type,
        pledge,
    } = props

    const navigate = useNavigate()
    const { onModalOpen, onCarBook, ...car } = props
    const carName = `${brand} ${name} ${year}`

    const handleClick = () => {
        fetch(`${URL}/user`, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`,
            },
        })
            .then((res) => res.json())
            .then((res) => {
                if (res.error) {
                    navigate('/login')
                } else {
                    onModalOpen()
                    onCarBook({ ...car, name: carName })
                }
            })
    }

    return (
        <div className={styles.car}>
            <img src={image} className={styles.image} alt="" />
            <h2>
                {brand} {name} {year}
            </h2>
            <div className={styles.iconsContainer}>
                <DescriptionIcon Icon={CarTypeIcon} name={type} />
                <DescriptionIcon Icon={EngineIcon} name={`${engine_speed} ??`} />
                <DescriptionIcon Icon={TransmissionIcon} name={gearbox} />
                <DescriptionIcon Icon={FuelIcon} name={fuel_type} />
                <DescriptionIcon Icon={PersonIcon} name={`${seats} ??????`} />
                <DescriptionIcon
                    Icon={LitreIcon}
                    name={`${fuel_consumption} ?? / 100????`}
                />
            </div>
            <div className={styles.priceContainer}>
                <p>?????? {price}???</p>
                <p>?????????????? {pledge}???</p>
            </div>
            <button
                className={styles.button}
                type={'button'}
                onClick={handleClick}
            >
                ????????????????????
            </button>
        </div>
    )
}
